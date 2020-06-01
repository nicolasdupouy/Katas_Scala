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
      case h :: t if h.isStrike && t.head.isStrike => h.sumRolls + t.head.getRoll1 + t.tail.head.getRoll1 + pm(t)
      case h :: t if h.isStrike => h.sumRolls + t.head.sumRolls + pm(t)
      case h :: t if h.isSpear => h.sumRolls + t.head.getRoll1 + pm(t)
      case h :: t => h.sumRolls + pm(t)
    }

    pm(this.frames)
  }

  def scoreRecursive(): Int = {
    @tailrec
    def loop(bowlingFrames: List[Frame], computedScore: Int): Int = {
      if (bowlingFrames.isEmpty)
        computedScore
      else if (bowlingFrames.head.isStrike && bowlingFrames.tail.nonEmpty && bowlingFrames.tail.head.isStrike)
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls + bowlingFrames.tail.head.getRoll1 + bowlingFrames.tail.tail.head.getRoll1)
      else if (bowlingFrames.head.isStrike && bowlingFrames.tail.nonEmpty)
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls + bowlingFrames.tail.head.sumRolls)
      else if (bowlingFrames.head.isSpear && bowlingFrames.tail.nonEmpty)
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls + bowlingFrames.tail.head.getRoll1)
      else
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls)
    }

    loop(this.frames, 0)
  }
}
