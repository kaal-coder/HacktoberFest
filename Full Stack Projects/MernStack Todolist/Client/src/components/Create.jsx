import React from 'react'
import { useState } from 'react';
import axios from 'axios';

function Create() {

    const [task, setTask] = useState();
    
    const handleAdd = () =>{
        //here we call server side url 
        axios.post('http://localhost:4000/add', {task:task})
        .then(result => {
            console.log("handle add result:: ",result)
            location.reload()
        })
        .catch(err => console.log(err))
    }
    return (
    <div>

        <h2>list</h2>
        <input 
        type="text" 
        id='' 
        placeholder='enter the task'
        onChange={ (e) => setTask(e.target.value)}
    
        />
        <button type='button'
        className='m-2'
        onClick={handleAdd}
        > Add</button>
    </div>
  )
}

export default Create




// handleAdd  -  now this store data ,we have to pass the server side app by using axios,  so that we can store in database,  

// before we write in input field , we need to store that in variabel: