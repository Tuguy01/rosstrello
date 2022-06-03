import React, { useState } from 'react';
import { DragDropContext, Droppable, Draggable} from "react-beautiful-dnd";
import Form from "./Components/Form";

const itemsFromBackend = [
    {id: '1', content: 'First task'},
    {id: '2', content: 'Second task'},
    {id: '3', content: 'Third task'},
    {id: '4', content: "Fourth task"}
]

const columnsFromBackend =
    {
        [1]: {
            name: 'Requested',
            items: itemsFromBackend
        },
        [2]: {
            name: 'To do',
            items: []
        },
        [3]: {
            name: 'In progress',
            items: []
        },
        [4]: {
            name: 'Under review',
            items:[]
        }
    };

const onDragEnd = (result, columns, setColumns) => {
    if (!result.destination) return;
    const {source, destination} = result;
    if (source.droppableId !== destination.droppableId){
        const sourceColumn = columns[source.droppableId];
        const destColumn = columns[destination.droppableId];
        const sourceItems = [...sourceColumn.items];
        const destItems = [...destColumn.items];
        const [removed] = sourceItems.splice(source.index, 1);
        destItems.splice(destination.index, 0, removed);
        setColumns({
            ...columns,
            [source.droppableId]: {
                ...sourceColumn,
                items: sourceItems
            },
            [destination.droppableId]: {
                ...destColumn,
                items: destItems
            }
        });
    }
    else {
    const column = columns[source.droppableId];
    const copiedItems = [...column.items];
    const [removed] = copiedItems.splice(source.index, 1);
    copiedItems.splice(destination.index, 0, removed);
    setColumns({
        ...columns,
        [source.droppableId]: {
            ...column,
            items: copiedItems
        }
    });}
};

function App() {
    const [columns, setColumns] = useState(columnsFromBackend);
  return (
      <div>
          <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', minHeight: 100, background: 'lightgray'}}>
              <h1>Name board</h1>
          </div>
    <div style={{display: 'flex', justifyContent: 'center', height: '100%'}}>
      <DragDropContext onDragEnd={result => onDragEnd(result, columns, setColumns)}>

          {Object.entries(columns).map(([id, column]) =>{
              return(
                  <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                      <h2>{column.name}</h2>
                      <div style={{margin: 8}}>
                  <Droppable droppableId={id} key={id} >
                      {(provided, snapshot) => {
                          return(
                              <div
                                  {...provided.droppableProps}
                                  ref={provided.innerRef}
                                  style={{
                                      background: snapshot.isDraggingOver ? 'lightblue' : 'lightgray',
                                      padding:4,
                                      width: 250,
                                      minHeight: 500
                                  }}
                              >
                                  {column.items.map((item, index) => {
                                      return (
                                          <Draggable
                                              key={item.id}
                                              draggableId={item.id}
                                              index={index}
                                          >
                                              {(provided, snapshot) => {
                                                  return (
                                                      <div
                                                          ref={provided.innerRef}
                                                          {...provided.draggableProps}
                                                          {...provided.dragHandleProps}
                                                          style={{
                                                              userSelect: 'none',
                                                              padding: 16,
                                                              margin: '0 0 8px 0',
                                                              minHeight: '50px',
                                                              backgroundColor: snapshot.isDragging
                                                                  ? '#263B4A'
                                                                  : '#456C86',
                                                              color: 'white',
                                                              ...provided.draggableProps.style
                                                          }}
                                                      >
                                                          {item.content}
                                                      </div>

                                                  );
                                              }}
                                          </Draggable>
                                      );
                                  })}
                                  {provided.placeholder}
                              </div>
                          )
                      }}
                  </Droppable>

                      </div>
                      <button href="#contained-buttons"

                          style={{
                              padding: 16,
                              margin: '0 0 8px 0',
                              minHeight: '50px',
                              backgroundColor: 'lightgray',
                              color: 'white',
                              width: 250
                          }}
                      >
                        Add
                      </button>
                  </div>

              )
          })}

      </DragDropContext>
        <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
            <h2> Add</h2>
            <div style={{margin: 8}}>
        <button

                style={{
                    padding: 4,
                    margin: '0 0 8px 0',
                    backgroundColor: 'lightgray',
                    color: 'white',
                    width: 50,
                    minHeight: 500
                }}
        >
            Add
        </button>
            </div>
        </div>
    </div>
      </div>
  );
}



export default App;


