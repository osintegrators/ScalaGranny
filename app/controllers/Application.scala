package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import play.api.libs.json.Json

import models.Address
import models.AddressDAO
import models.AddressDAO._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def addresses = Action {
    Ok(Json.toJson(AddressDAO.all()))
  }

  def create = Action(parse.json) { implicit request =>
    val address = request.body.as[Address]
    Logger.info(s"Address ID: %address.id")
    val createdAddress = AddressDAO.create(address)
    Ok(Json.toJson(createdAddress))
  }

  def save(id: Long) = Action(parse.json) { implicit request =>
    val address = request.body.as[Address]
    AddressDAO.save(address)
    Ok(Json.toJson(address))
  }

  def getById(id: Long) = Action {
    Ok(Json.toJson(AddressDAO.getById(id)))
  }

  def deleteAddress(id: Long) = Action {
    AddressDAO.delete(id)
    Ok(s"Address $id Deleted")
  }

}