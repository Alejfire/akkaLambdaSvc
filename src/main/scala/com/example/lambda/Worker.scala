package com.example.lambda

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import Messages._

object Worker {
  def apply(): Behavior[Any] =
    Behaviors.setup { _ =>
      Behaviors.receiveMessage {
        case SumRequest(a, b, replyTo) =>
          if (a == 13) throw new IllegalStateException("boom: unlucky 13")
          replyTo ! SumResult(a + b)
          Behaviors.same
        case _ => Behaviors.unhandled
      }
    }
}
