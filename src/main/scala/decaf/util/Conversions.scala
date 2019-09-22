package decaf.util

import scala.jdk.CollectionConverters._

object Conversions {
  implicit def ScalaListToJavaList[T](list: List[T]): java.util.List[T] = list.asJava

  implicit def ScalaOptionToJavaOptional[T](option: Option[T]): java.util.Optional[T] = option match {
    case Some(value) => java.util.Optional.of(value)
    case None => java.util.Optional.empty()
  }
}
