package gameoflife

import scala.reflect.ClassTag

case class Neighbours[A](
  north: Option[A],
  northEast: Option[A],
  east: Option[A],
  southEast: Option[A],
  south: Option[A],
  southWest: Option[A],
  west: Option[A],
  northWest: Option[A]
) {

  val all: List[A] =
    List(
      north,
      northEast,
      east,
      southEast,
      south,
      southWest,
      west,
      northWest
    ).flatten

  def combine(f: (A, A) => A): Option[A] = {
    if(all.isEmpty) None
    else Some(all.reduceLeft[A] { case (acc, curr) => f(acc, curr) })
  }

}

object ArrayOps {

  implicit class MArrayOps[A](val arr: Array[Array[A]]) extends AnyVal {

    def at(i: Int, j: Int): Option[A] = arr.lift(i).flatMap(_.lift(j))

    def mapNeighbours[B](f: (A, Neighbours[A]) => B)(implicit ct: ClassTag[B]): Array[Array[B]] = { 
      arr.zipWithIndex.map { case (as, i) =>
        as.zipWithIndex.map { case (a, j) =>

          val n =
            Neighbours(
              arr.at(i - 1, j    ),
              arr.at(i - 1, j + 1),
              arr.at(i    , j + 1),
              arr.at(i + 1, j + 1),
              arr.at(i + 1, j    ),
              arr.at(i + 1, j - 1),
              arr.at(i    , j - 1),
              arr.at(i - 1, j - 1),
            )

          f(a, n)
        } 
      }
    }
  }
}