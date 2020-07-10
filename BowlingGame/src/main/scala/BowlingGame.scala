import scala.annotation.tailrec

class BowlingGame {
  private var frames = List[Frame]()

  def roll(pins: Int): Unit = {
    if (frames.nonEmpty && frames.last.isLastFrame) {
      val l = frames.last.asInstanceOf[TenthFrame]
      if (!l.roll2Done) {
        l.roll2Done = true
        l.addRoll(pins)
      }
      else l.roll3 = pins
    }
    else if (
      frames.nonEmpty
        && frames.last.isNotFinished)
      frames.last.addRoll(pins)
    else if (frames.size == 9)
      frames = frames :+ new TenthFrame(pins)
    else
      frames = frames :+ Frame(pins)
  }

  def scorePatternMatching(): Int = {
    def pm(bowlingFrames: List[Frame]): Int = bowlingFrames match {
      case Nil => 0
      case h :: Nil if h.isLastFrame && (h.isStrike || h.isSpear) => h.sumRolls + h.asInstanceOf[TenthFrame].roll3
      case h :: Nil => h.sumRolls
      case h :: t if h.isStrike => scoreWhenStrike(h, t.head, t.tail) + pm(t)
      case h :: t if h.isSpear => scoreWhenSpear(h, t.head) + pm(t)
      case h :: t => h.sumRolls + pm(t)
    }

    pm(this.frames)
  }

  def scoreRecursive(): Int = {
    @tailrec
    def loop(bowlingFrames: List[Frame], computedScore: Int): Int = {
      if (bowlingFrames.isEmpty)
        computedScore
      else if (bowlingFrames.head.isLastFrame && (bowlingFrames.head.isStrike || bowlingFrames.head.isSpear))
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls + bowlingFrames.head.asInstanceOf[TenthFrame].roll3)
      else if (bowlingFrames.head.isStrike && bowlingFrames.tail.nonEmpty)
        loop(bowlingFrames.tail, computedScore + scoreWhenStrike(bowlingFrames.head, bowlingFrames.tail.head, bowlingFrames.tail.tail))
      else if (bowlingFrames.head.isSpear && bowlingFrames.tail.nonEmpty)
        loop(bowlingFrames.tail, computedScore + scoreWhenSpear(bowlingFrames.head, bowlingFrames.tail.head))
      else
        loop(bowlingFrames.tail, computedScore + bowlingFrames.head.sumRolls)
    }

    loop(this.frames, 0)
  }

  private def scoreWhenSpear(currentFrame: Frame, nextFrame: Frame): Int = currentFrame.sumRolls + nextFrame.getRoll1

  private def scoreWhenStrike(currentFrame: Frame, nextFrame: Frame, tail: List[Frame]): Int = {
    if (nextFrame.isStrike && nextFrame.isLastFrame)
      currentFrame.sumRolls + nextFrame.sumRolls
    else if (nextFrame.isStrike)
      currentFrame.sumRolls + nextFrame.getRoll1 + tail.head.getRoll1
    else
      currentFrame.sumRolls + nextFrame.sumRolls
  }
}
