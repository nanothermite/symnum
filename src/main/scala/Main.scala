import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object Main extends App {
  var inputs = ArrayBuffer[String]()
  for (ln <- io.Source.stdin.getLines()) {
    inputs.append(ln)
  }
  val numVals = inputs(0).toInt
  val numSeq = inputs.tail.toList

  numSeq.foreach(sym => println(nextSym(sym)))

  def nextSym(num: String) : String = {
    val (l, r) = lr(num)
    val oneSide = compareLR(l, r.reverse, l.length - 1)
    oneSide + (if (num.length % 2 == 0) oneSide.reverse else  oneSide.reverse.drop(1))
  }

  def lr(inp: String): (String, String) = {
    val len = inp.length
    val mid = (len + 1) / 2
    val left = inp.take(mid)
    val right = inp.drop(len - mid)
    (left, right)
  }

  @tailrec
  def compareLR(left: String, right: String, i: Int): String = {
    if (i < 0) left
    else {
      if (left(i) > right(i)) left
      else if (left(i) <= right(i)) (BigInt.apply(left) + 1).toString
      else compareLR(left, right, i - 1)
    }
  }
}