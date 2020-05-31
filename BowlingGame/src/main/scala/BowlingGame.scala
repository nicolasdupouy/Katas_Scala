class BowlingGame {
  private var frames = List[Frame]()

  def roll(pins: Int): Unit = {
    if (
      frames.nonEmpty
      && frames.last.roll2 == Nil)
      frames.last.roll2 = pins
    else
      frames = frames :+ Frame(pins)
  }

  def score(): Int = {
    var computedScore: Int = 0
    for (frame <- frames) {
      computedScore += frame.roll1
      if (frame.roll2 != Nil)
        computedScore += frame.roll2
    }
    computedScore
  }
}
