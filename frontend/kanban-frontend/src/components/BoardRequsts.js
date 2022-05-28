import axios from "axios";
import authHeader from '../../authentication/services/auth-header.js';

const API_URL = "http://<IP:PORT>/api/";


const config = {
    headers: authHeader(),
}

//Получить список столбцов с карточками для данной доски
const getColumnList = (board_id) =>{
    return axios
        .get(API_URL + "v1/boards/board/" + board_id, config)
}


//Добавить колонку к доске
const addColumn = (board_id, name) =>{
    return axios
        .post(API_URL + "v1/columns?boardID=" + board_id, name, config)
}

//Изменить колонку
const changeColum = (board_id, id, name, cards) =>{
    let data = {
        'id': id,
        'name':name,
        'cards':cards
    }

    return axios
        .put(API_URL + "v1/columns?boardID=" + board_id, data, config)
}

//Удалить колонку
const deleteColumn =  (columnID) =>{
    return axios
        .delete(API_URL + "v1/columns?columnID=" + columnID)
}

const  BoardApi=  {
    deleteColumn,
    changeColum,
    addColumn,
    getColumnList
};

export default BoardApi;
