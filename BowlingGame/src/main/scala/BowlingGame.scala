import scala.annotation.tailrec

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

  def scorePatternMatching(): Int = {
    def pm(bowlingFrames: List[Frame]): Int = bowlingFrames match {
      case Nil => 0
      case h :: Nil => h.getRoll1 + h.getRoll2
      case h :: t if h.isStrike => h.getRoll1 + h.getRoll2 + t.head.getRoll1 + t.head.getRoll2 + pm(t)
      case h :: t if h.isSpear => h.getRoll1 + h.getRoll2 + t.head.getRoll1 + pm(t)
      case h :: t => h.getRoll1 + h.getRoll2 + pm(t)
    }

    pm(this.frames)
  }

  def scoreRecursive(): Int = {
    @tailrec
    def loop(bowlingFrames: List[Frame], computedScore: Int): Int = {
      if (bowlingFrames.isEmpty)
        computedScore
      else if (bowlingFrames.head.isStrike && bowlingFrames.tail.nonEmpty)
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.getRoll1 + bowlingFrames.head.getRoll2 + bowlingFrames.tail.head.getRoll1 + bowlingFrames.tail.head.getRoll2)
      else if (bowlingFrames.head.isSpear && bowlingFrames.tail.nonEmpty)
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.getRoll1 + bowlingFrames.head.getRoll2 + bowlingFrames.tail.head.getRoll1)
      else
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.getRoll1 + bowlingFrames.head.getRoll2)
    }

    loop(this.frames, 0)
  }
}
