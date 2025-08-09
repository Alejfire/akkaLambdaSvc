package com.example.lambda

import akka.actor.typed.ActorSystem
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Await
import akka.actor.typed.scaladsl.AskPattern._
import scala.concurrent.ExecutionContext
import Messages._
import Supervisor._

object Main extends App {
  implicit val timeout: Timeout = 3.seconds
  implicit val ec: ExecutionContext = ExecutionContext.global

  val system = ActorSystem(Supervisor(), "test-system")

  val resultF = system.ask[SumRequest, SumResult](replyTo => DispatchSum(2, 3, replyTo))
  println("Resultado: " + Await.result(resultF, 4.seconds).result)

  val crashF = system.ask[SumRequest, SumResult](replyTo => DispatchSum(13, 5, replyTo))
  try {
    println(Await.result(crashF, 4.seconds).result)
  } catch {
    case e: Exception => println("Error esperado: " + e.getMessage)
  }

  system.terminate()
}
