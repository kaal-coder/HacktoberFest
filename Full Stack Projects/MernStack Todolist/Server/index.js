const express = require('express');
const mongoose = require('mongoose')
const cors = require('cors')
const TodoModel = require('./Models/Todo')

const app = express();
app.use(cors());
app.use(express.json())

MONGODB_URL ="mongodb+srv://aman:Iw73Uiig2b6KR6XR@cluster0.rssl8qk.mongodb.net/todolist1"
mongoose.connect(MONGODB_URL);

//update api

app.put('/update/:id', (req,res) => {
const {id} = req.params;
console.log("after",id)
  TodoModel.findByIdAndUpdate({_id:id}, {done:true})
  .then(result => res.json(result))
  .catch(err => res.json(err))
})


app.delete('/delete/:id', (req,res) => {
  const {id} = req.params;
  console.log("after",id)
    TodoModel.findByIdAndDelete({_id:id})
    .then(result => res.json(result))
    .catch(err => res.json(err))
  })

//get api create
app.get('/get',(req,res) =>{
  TodoModel.find()
  .then(result => res.json(result))
  .catch(err => res.json(err))
})

//api create
app.post('/add', (req,res) =>{
    const task = req.body.task;  // req our data
    TodoModel.create({        //we have to add the db
        task: task
    }).then(result => res.json(result))
    .catch(err =>res.json(err))
})


const PORT = 4000;

app.listen(PORT, () => {
  console.log(`CORS-enabled web server listening on port ${PORT}`);
});
