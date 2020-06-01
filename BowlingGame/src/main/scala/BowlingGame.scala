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
      case h :: t => h.getRoll1 + h.getRoll2 + pm(t)
    }

    pm(this.frames)
  }

  def scoreRecursive(): Int = {
    @tailrec
    def loop(bowlingFrames: List[Frame], computedScore: Int): Int = {
      if (bowlingFrames.isEmpty) computedScore
      else loop(bowlingFrames.tail, computedScore + bowlingFrames.head.getRoll1 + bowlingFrames.head.getRoll2)
    }

    loop(this.frames, 0)
  }
}
