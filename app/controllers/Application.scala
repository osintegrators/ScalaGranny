package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import play.api.libs.json._

import models.Address;

object Application extends Controller {

    def index = Action {
        Ok(views.html.index())
    }

    def addresses = Action {
        Ok(Json.toJson(Address.all()))
    }

    def create = Action(parse.json) { implicit request =>
        val address = request.body.as[Address]
        System.out.println("Address ID: " + address.id)
    	Address.create(address)
    	Ok(Json.toJson(address))
    }

    def save = Action(parse.json) { implicit request =>
        val address = request.body.as[Address]
    	Address.save(address)
    	Ok(Json.toJson(address))
    }

    def getById(id: Long) = Action {
        Ok(Json.toJson(Address.getById(id)))
    }

    def deleteAddress(name: String) = Action {
        Address.delete(name)
        Ok("Address Deleted")
    }

    val addressForm = Form(
        "name" -> nonEmptyText
    )
}