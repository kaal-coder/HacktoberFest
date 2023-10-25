import React, { useEffect } from 'react'
import Create from './Create'
import { useState } from 'react'
import axios from 'axios'
import {BsCircleFill } from 'react-icons/bs'
import {BsFillTrashFill } from 'react-icons/bs'
import {BsFillCheckCircleFill} from 'react-icons/bs'

function Home() {
const [todos, setTodos] = useState([])

//we are fetch our data from server side to show in frontend 
useEffect( () =>{
  axios.get('http://localhost:4000/get')
  .then(result => setTodos(result.data))
  .catch(err => console.log(err))
},[])


//for update we have to pass the id - to the server side
const handleEdit = (id) => {
  axios.put('http://localhost:4000/update/'+id)
  .then(result => {
    console.log(result)
    location.reload()
  })
  .catch(err => console.log(err))
}
const handleDelete = (id) => {
  axios.delete('http://localhost:4000/delete/'+id)
  .then(result => {
    console.log(result)
    location.reload()
  })
  .catch(err => console.log(err))
}

  return (
    <div>
        <h1> Todo List</h1>
        <Create/>
        {
            todos.length === 0 
            ? <div><h2>No Record</h2></div>
            : 
            todos.map( todo =>(
                <div className='task'>
                    <div className='checkbox' onClick={ (_id)=> handleEdit(todo._id)}>
                      {todo.done ?  <BsFillCheckCircleFill/> : <BsCircleFill className='icon'/>}
                      
                      <p className={todo.done ? "line_through" : ""} > {todo.task} </p>
                    </div>

                    <div >
                      <span> 
                        <BsFillTrashFill className='icon'
                        onClick={ ()=> handleDelete(todo._id) }
                        />
                        </span>
                    </div>
                  </div>
            ))
        }

    </div>
  )
}

export default Home


//pitch and store them in array