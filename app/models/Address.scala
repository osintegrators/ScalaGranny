package models

import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current

import play.api.libs.json._
import play.api.libs.json.util._
import play.api.libs.json.Reads._
import play.api.data.validation.ValidationError

case class Address(name: String, address: String = null, phone: String = null, email: String = null, id: Long = 50)

object Address {

    implicit object AddressFormat extends Format[Address] {
		def reads(json: JsValue) = JsSuccess(Address(
			(json \ "name").as[String],
			(json \ "address").as[String],
			(json \ "phone").as[String],
			(json \ "email").as[String],
			(json \ "id").as[Long]
		))

        def writes(address: Address): JsValue = JsObject(Seq(
            "id" -> JsNumber(address.id),
            "name" -> JsString(address.name),
            "address" -> JsString(address.address),
            "phone" -> JsString(address.phone),
            "email" -> JsString(address.email)
        ))
    }

    val address = {
        get[Long]("id") ~
        get[String]("name") ~
        get[String]("address") ~
        get[String]("phone") ~
        get[String]("email") map {
            case id~name~address~phone~email => Address(name, address, phone, email, id)
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

    def create(address: Address) {
        DB.withConnection { implicit c =>
            SQL("insert into address (name, address, phone, email) values ({name}, {address}, {phone}, {email})").on(
                'name -> address.name,
                'address -> address.address,
                'phone -> address.phone,
                'email -> address.email
            ).executeUpdate()
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

    def delete(name: String) {
        DB.withConnection { implicit c =>
            SQL("delete from address where name = {name}").on(
                'name -> name
            ).executeUpdate()
        }
    }


}