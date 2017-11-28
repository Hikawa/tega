package tk.aankor.tega.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import io.github.aedans.cons.Nil
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.oneOf
import ktx.math.minus
import ktx.math.plus
import ktx.math.times
import tk.aankor.tega.components.AnimationComponent
import tk.aankor.tega.components.AttackingComponent
import tk.aankor.tega.components.MovingComponent
import tk.aankor.tega.components.TransformComponent

class CommandSystem: IteratingSystem(
  allOf(TransformComponent::class)
    .oneOf(
      MovingComponent::class,
      AttackingComponent::class)
    .get(), 0)
{
  private fun processMoving(moving: MovingComponent, entity: Entity, deltaTime: Float) {
    val path = moving.path
    when (path) {
      Nil -> entity.remove(MovingComponent::class.java)
      else -> {
        val target = path.car
        val transform = entity[TransformComponent.mapper]!!
        val step = (target.cpy() - transform.posiotion).nor() * moving.speed * deltaTime
        val animation = entity[AnimationComponent.mapper]?: AnimationComponent()
        animation.animation = moving.animationPack[step]
        entity.add(animation)
        if ((target.cpy() - transform.posiotion).len() < moving.speed * deltaTime) {
          transform.posiotion = target
          moving.path = moving.path.cdr
          return
        }
        transform.posiotion += step
      }
    }
  }

  private fun processAttacking(attacking: AttackingComponent, entity: Entity, deltaTime: Float) {
    val transform = entity[TransformComponent.mapper]!!
    val animation = entity[AnimationComponent.mapper]?: AnimationComponent()
    animation.animation = attacking.animationPack[attacking.target.cpy() - transform.posiotion]
    animation.endingListener = {
      entity.remove(AttackingComponent::class.java)
    }
    entity.add(animation)
  }

  override fun processEntity(entity: Entity, deltaTime: Float) {
    val moving = entity[MovingComponent.mapper]
    if (moving != null) {
      processMoving(moving, entity, deltaTime)
      return
    }

    val attacking = entity[AttackingComponent.mapper]
    if (attacking != null) {
      processAttacking(attacking, entity, deltaTime)
      return
    }
  }
}