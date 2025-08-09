package com.example.lambda

import akka.actor.typed.ActorRef

object Messages {
  final case class SumRequest(a: Int, b: Int, replyTo: ActorRef[SumResult])
  final case class SumResult(result: Int)
}
