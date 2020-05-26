class FizzBuzz {
  val FIZZ: String= "Fizz"
  val BUZZ: String = "Buzz"
  val FIZZ_BUZZ: String = "FizzBuzz"

  def display(i: Int): String = {
    def isMultipleOfThreeAndFive(x: Int): Boolean = isMultipleOfThree(x) && isMultipleOfFive(x)
    def isMultipleOfThree(x: Int): Boolean = x % 3 == 0
    def isMultipleOfFive(x: Int): Boolean = x % 5 == 0

    i match {
      case x if isMultipleOfThreeAndFive(x) => FIZZ_BUZZ: String
      case x if isMultipleOfThree(x) => FIZZ
      case x if isMultipleOfFive(x) => BUZZ
      case x => x.toString
    }
  }
}
