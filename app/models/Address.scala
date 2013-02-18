package models

import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current

import play.api.libs.json.Json


case class Address(id: Long, name: String, address: String, phone: String, email: String)

object AddressDAO {

  implicit val addressReads = Json.reads[Address]
  implicit val addressWrites = Json.writes[Address]

  val address = {
    get[Long]("id") ~
    get[String]("name") ~
    get[String]("address") ~
    get[String]("phone") ~
    get[String]("email") map {
      case id~name~address~phone~email => Address(id, name, address, phone, email)
    }
  }

  def all(): List[Address] = DB.withConnection { implicit c =>
    SQL("select * from address").as(address *)
  }

  def getById(id: Long): Address = DB.withConnection { implicit c =>
    SQL("select * from address where id = {id}").on(
      'id -> id
    ).as(address.single)
  }

  def create(address: Address): Option[Address] = {
    DB.withConnection { implicit c =>
      SQL("insert into address (name, address, phone, email) values ({name}, {address}, {phone}, {email})").on(
        'name -> address.name,
        'address -> address.address,
        'phone -> address.phone,
        'email -> address.email
      ).executeInsert().map { id =>
        getById(id)
      }
    }
  }

  def save(address: Address) {
    DB.withConnection { implicit c =>
      SQL("update address set name={name}, address={address}, phone={phone}, email={email} where id = {id}").on(
        'id -> address.id,
        'name -> address.name,
        'address -> address.address,
        'phone -> address.phone,
        'email -> address.email
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from address where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

}