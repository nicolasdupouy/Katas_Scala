class BowlingGame {
  private var frames = List[Frame]()

  def roll(pins: Int): Unit = {
    if (
      frames.nonEmpty
      && frames.last.isNotFinished)
      frames.last.setRoll2(pins)
    else
      frames = frames :+ Frame(pins)
  }

  def score(): Int = {
    var computedScore: Int = 0
    for (frame <- frames) {
      computedScore += frame.getRoll1
      if (frame.isFinished)
        computedScore += frame.getRoll2
    }
    computedScore
  }
}
