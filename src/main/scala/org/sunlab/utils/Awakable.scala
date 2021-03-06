package org.sunlab.utils

import org.slf4j.{ Logger, LoggerFactory }

/**
 * Here is the '''start''' of some __object__ and we defined ''some'' `common functions`
 *
 * Here is a example use this trait
 * {{{
 * import org.aminer.io.utils.Awakable
 *
 * trait MyObject extends Awakable{
 *  def someMoreCommonFuns(s:String) = {
 *    "some_return" + s
 *  }
 * }
 *
 * object MyObject extends MyObject {
 *  def useAwakableAndMyObject = {
 *    awake(someMoreCommonFuns("__from_object__"))
 *  }
 * }
 *
 * }}}
 *
 * Additionally, this is ^^also^^ a ,,example,, of '''How to write scala documents?'''
 *
 * About doc writing, for more detail, please visit https://wiki.scala-lang.org/display/SW/Syntax
 *
 * @author yu
 */
trait Awakable {
  lazy val logger: Logger = LoggerFactory.getLogger(this.getClass)

  /**
   * log a tip to tell us '''I am started'''
   *
   * @param name who am i
   * @return the function is successfully executed
   * @author yu
   */
  def awake(name: String = "The World") = {
    logger.info(s"[$name] is waking up ...")
    true
  }

}