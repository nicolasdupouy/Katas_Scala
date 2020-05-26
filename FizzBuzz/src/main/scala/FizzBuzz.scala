class FizzBuzz {
  def display(i: Int): String = i match {
    case x if x % 3 == 0 => "Fizz"
    case x if x % 5 == 0 => "Buzz"
    case x => x.toString
  }
}
