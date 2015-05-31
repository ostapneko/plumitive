package plumitive.core

import plumitive.Document

object SearchQuery {
  import Document._
  sealed trait Direction
  case object Ascending
  case object Descending

  sealed trait SortOrder
  case object ByRelevance extends SortOrder
  case class ByDate(direction: Direction) extends SortOrder

  sealed trait Filter
  case class HasRecipients(recipients: Set[Recipient])
  case class HasSender(sender: Sender)
  case class HasTags(tags: Set[Tag])
  case class BetweenDates(min: Date, max: Date)
  case class NewerThan(min: Date)
  case class OlderThan(max: Date)
}

case class SearchQuery(
    searchTerms: Seq[String],
    filters: Set[SearchQuery.Filter],
    sortOrder: Option[SearchQuery.SortOrder]
  )
