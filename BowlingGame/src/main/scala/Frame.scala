case class Frame(private val pins: Int) {
  private val roll1: Int = pins
  private var roll2: Int = 0
  private var finished = if (isStrike) true else false

  def getRoll1: Int = roll1
  def getRoll2: Int = roll2

  def setRoll2(roll2: Int): Unit = {
    this.roll2 = roll2
    this.finished = true
  }

  def isFinished: Boolean = finished
  def isNotFinished: Boolean = !finished

  def isStrike: Boolean = roll1 == 10
  def isSpear: Boolean = !isStrike && (roll1 + roll2 == 10)
}
