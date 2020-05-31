class BowlingGame {
  var pinsKnockedDown = 0

  def roll(pins: Int): Unit = {
    pinsKnockedDown += pins
  }

  def score(): Int = pinsKnockedDown
}
