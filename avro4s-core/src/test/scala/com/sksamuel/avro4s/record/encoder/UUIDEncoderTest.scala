package com.sksamuel.avro4s.record.encoder

import java.util.UUID

import com.sksamuel.avro4s.internal.{Encoder, InternalRecord, SchemaEncoder}
import org.scalatest.{Matchers, WordSpec}

class UUIDEncoderTest extends WordSpec with Matchers {

  "RecordEncoder" should {
    "encode uuids" in {
      val uuid = UUID.randomUUID()
      val schema = SchemaEncoder[UUIDTest].encode()
      Encoder[UUIDTest].encode(UUIDTest(uuid), schema) shouldBe InternalRecord(schema, Vector(uuid))
    }
    "encode seq of uuids" in {
      val uuid1 = UUID.randomUUID()
      val uuid2 = UUID.randomUUID()
      val schema = SchemaEncoder[UUIDSeq].encode()
      Encoder[UUIDSeq].encode(UUIDSeq(Seq(uuid1, uuid2)), schema) shouldBe InternalRecord(schema, Vector(uuid1))
    }
    "encode UUIDs with defaults" in {
      val uuid = UUID.randomUUID()
      val schema = SchemaEncoder[UUIDDefault].encode()
      Encoder[UUIDDefault].encode(UUIDDefault(uuid), schema) shouldBe InternalRecord(schema, Vector(uuid))
    }
//    "encode Option[UUID]" in {
//      val uuid = UUID.randomUUID()
//      val schema = SchemaEncoder[UUIDOption].encode()
//      Encoder[UUIDOption].encode(UUIDOption(Some(uuid)), schema) shouldBe InternalRecord(schema, Vector(uuid))
//      Encoder[UUIDOption].encode(UUIDOption(None), schema) shouldBe InternalRecord(schema, Vector(uuid))
//    }
  }
}

case class UUIDTest(uuid: UUID)
case class UUIDSeq(uuids: Seq[UUID])
case class UUIDDefault(uuid: UUID = UUID.fromString("86da265c-95bd-443c-8860-9381efca059d"))
case class UUIDOption(uuid: Option[UUID])