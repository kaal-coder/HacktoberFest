import React from 'react'

const Description = () => {
  return (
    <div className="flex flex-wrap" >
        <div className="md:basis-1/3 px-16 py-4 text-center">
            <i className="fa fa-star text-2xl text-primary"></i>
             <p className="text-lg font-Inter text-primary font-medium">5 Star Rated</p>
             <p className="text-base">Our products are awesome, and 10k+ reviews show that you don't have to just take our word for it!</p>
        </div>
        <div className="md:basis-1/3 px-16 py-4 text-center">
            <i className="fa fa-shield text-2xl text-primary" ></i>
             <p className="text-lg font-Inter text-primary font-medium">5 Star Rated</p>
             <p className="text-base">Our products are awesome, and 10k+ reviews show that you don't have to just take our word for it!</p>
        </div>
        <div className="md:basis-1/3 px-16 py-4 text-center">
            <i className="fa fa-lock text-2xl text-primary" ></i>
             <p className="text-lg font-Inter text-primary font-medium">5 Star Rated</p>
             <p className="text-base">Our products are awesome, and 10k+ reviews show that you don't have to just take our word for it!</p>
        </div>
    </div>
  )
}

export default Description