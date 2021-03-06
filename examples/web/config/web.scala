/* web.scala

   Use the simple record processor, which takes a log with one URL per line. Host names and protocol
   are omitted. More properly

  foo://username:password@example.com:8042/over/there/index.dtb?type=animal&name=narwhal#nose
  \_/   \_______________/ \_________/ \__/            \___/ \_/ \______________________/ \__/
   |           |               |       |                |    |            |                |
   |       userinfo         hostname  port              |    |          query          fragment
   |    \________________________________/\_____________|____|/ \__/        \__/
   |                    |                          |    |    |    |          |
   |                    |                          |    |    |    |          |
scheme              authority                    path   |    |    interpretable as keys
 name   \_______________________________________________|____|/       \____/     \_____/
   |                         |                          |    |          |           |
   |                 hierarchical part                  |    |    interpretable as values
   |                                                    |    |
   |            path               interpretable as filename |
   |   ___________|____________                              |
  / \ /                        \                             |
  urn:example:animal:ferret:nose               interpretable as extension

the scheme and authority portions of the URI are omitted. The simplest example is "/".

*/

import com.twitter.parrot.config.ParrotLauncherConfig

new ParrotLauncherConfig {

  doConfirm = false

  duration = 30
  timeUnit = "SECONDS"

  victims = "google.com"
  log = "../replay.log"
  reuseFile = true

  jobName = "web"
  localMode = true

//  Comment-out the trace level when you increase the request rate.

  traceLevel = com.twitter.logging.Level.ALL

  requestRate = 1	// 50000 requests per second per parrot server
                        // is the practical upper limit

imports="""
import com.twitter.parrot.processor.SimpleRecordProcessor
import org.jboss.netty.handler.codec.http.HttpResponse
"""
  loadTest = "new SimpleRecordProcessor(service.get, this)"

}
