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

  def score(): Int = {
    @tailrec
    def loop(frames: List[Frame], computedScore: Int): Int = {
      if (frames.isEmpty) computedScore
      else loop(frames.tail, computedScore + frames.head.getRoll1 + frames.head.getRoll2)
    }

    loop(this.frames, 0)
  }
}
