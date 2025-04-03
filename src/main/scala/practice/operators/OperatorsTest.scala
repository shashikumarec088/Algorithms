package practice.operators
import scala.language.postfixOps

object OperatorsTest extends App{

  // arithmetic addition operator (+)
  assert(1 + 2 == 3)

  // using with .
  assert(1.+(2) == 3)

  // string example
  assert("Baeldung".charAt(0) == 'B')

  val char = "Baeldung" charAt 0
  assert(char == 'B')

  // we have to put all the parameters between a pair of parentheses
  // to use the method in infix notation
  assert("Baeldung".replace('g', 'G') == "BaeldunG")

  val str = "Baeldung" replace ('g', 'G')
  assert(str == "BaeldunG")

  //Postfix and Prefix Operator Notation
  assert(10.unary_- == -10)

  // for postfix to work we need to import import scala.language.postfixOps
  val strUpperCase : String = "baeldung" toUpperCase


}
