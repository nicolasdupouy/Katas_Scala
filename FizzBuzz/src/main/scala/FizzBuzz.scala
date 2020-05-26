class FizzBuzz {
  val FIZZ: String= "Fizz"
  val BUZZ: String = "Buzz"
  val FIZZBUZZ: String = "FizzBuzz"

  def display(i: Int): String = i match {
    case x if x % 3 == 0 && x % 5 == 0 => FIZZBUZZ: String
    case x if x % 3 == 0 => FIZZ
    case x if x % 5 == 0 => BUZZ
    case x => x.toString
  }
}
