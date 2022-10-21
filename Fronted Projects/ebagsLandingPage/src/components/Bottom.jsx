import React from 'react'

const Bottom = () => {
  return (
    <div className="flex flex-wrap justify-center items-center h-48 md:h-28 bg-primary">
         <p  className="text-white font-semibold  w-96 text-lg md:text-2xl mr-10">Stay ahead and get 15% off when you sign up for emails!</p>
         <div className="w-auto">
            <i></i>
            <input className="bg-primary border-b border-white w-64 px-5 text-white" placeholder='please enter an email address' type="text" />
            <button className="px-3 py-1 mx-5 mt-2 bg-white text-primary" >Sign Up</button>
         </div>
    </div>
  )
}

export default Bottom