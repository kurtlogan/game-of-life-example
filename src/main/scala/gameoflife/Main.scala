package gameoflife

import ArrayOps._
import scala.util.Random

object Main extends App {

  val array = Array.fill(30) { Array.fill(50) { Random.between(0, 2) } }

  (1 to 100000).foldLeft(array) { case (acc, curr) =>

    print("\u001b[2J")
    println(acc.map(_.map(a => if(a==1) 'X' else '.').mkString).mkString("\n"))

    Thread.sleep(800)

    acc.mapNeighbours { (i, n) =>
      n.all.filter(_ == 1).size match {
        case 2 => i
        case 3 => 1
        case _ => 0
      }
    }
  }
}
