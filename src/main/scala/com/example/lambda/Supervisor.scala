package com.example.lambda

import akka.actor.typed.{Behavior, SupervisorStrategy}
import akka.actor.typed.scaladsl.{Behaviors, Routers}
import Messages._
import akka.actor.typed.ActorRef

object Supervisor {
  sealed trait Command
  final case class DispatchSum(a: Int, b: Int, replyTo: ActorRef[SumResult]) extends Command

  def apply(workerCount: Int = 4): Behavior[Command] = Behaviors.setup { ctx =>
    val supervisedWorker = Behaviors.supervise(Worker()).onFailure[IllegalStateException](SupervisorStrategy.restart)
    val pool = Routers.pool(workerCount)(supervisedWorker)
    val router = ctx.spawn(pool, "worker-pool")

    Behaviors.receiveMessage {
      case DispatchSum(a, b, replyTo) =>
        router ! SumRequest(a, b, replyTo)
        Behaviors.same
    }
  }
}
