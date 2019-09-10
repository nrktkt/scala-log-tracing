import monix.execution.misc.Local
import monix.execution.schedulers.TrampolineExecutionContext

import scala.concurrent.Future

package object common {
  def propagate[A](future: Future[A]): Future[A] = {
    val ctx = Local.getContext()
    future.transform { a =>
      Local.setContext(ctx)
      a
    }(TrampolineExecutionContext.immediate)
  }
}
