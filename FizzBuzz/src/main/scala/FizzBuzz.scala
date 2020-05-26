class FizzBuzz {
  def display(i: Int): String = i match {
    case x if x % 3 == 0 => "Fizz"
    case x => x.toString
  }
}
