import axios from "axios";
import authHeader from '../../authentication/services/auth-header.js';

const API_URL = "http://<IP:PORT>/api/";

const config = {
    headers: authHeader(),
}

//Создание карточки для колонки
 const cardCreate = (columnID, name, description) =>{
     let data = {
         'name':name,
         'description':description
     }
    return axios
        .post(API_URL + "v1/cards?columnID=" + columnID, data, config)
 }

//Изменение карточки
const changeCard = (id, name, description) =>{
    let data = {
        'id':id,
        'name':name,
        'description':description
    }
    return axios
        .put(API_URL + "v1/cards", data, config)
}

//Удаление карточки
const cardDelete = (cardID) =>{
    return axios
        .delete(API_URL + "v1/cards?cardID=" + cardID, config)
}


const CardsApi = {
    cardDelete,
    changeCard,
    cardCreate,
};

export default CardsApi;