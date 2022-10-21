import React from 'react'

const data = [
    {
        head  : "product info",
        links : [
            {name : "product care & cleaning"},
            {name : "warranty for ebags brand"},
            {name : "warranty for other brands"},
            {name : "airline carry-on guide"},
        ]
    },
    {
        head  : "ORDERS",
        links : [
            {name : "Track Your Order"},
            {name : "Shipping & Delivery"},
            {name : "Returns Policy"},
        ]
    },
    {
        head  : "CUSTOMER SERVICE",
        links : [
            {name : "Contact Us"},
            {name : "FAQs"},
        ]
    },
    {
        head  : "ABOUT EBAGS",
        links : [
            {name : "About Us"},
            {name : "Join our Affiliates Program"},
        ]
    },
    {
        head  : "SITE TERMS",
        links : [
            {name : "Privacy Policy"},
            {name : "Terms of Use"},
            {name : "Accessibility Statement"},
        ]
    },
]



const Footer = () => {
  return (
    <div className="grid grid-cols-2 md:grid-cols-5  justify-items-center" >
         {data.map((f)=>{
             return  <div className=" md:max-w-sm md:border-r-2 px-10 py-2" >
             <div className="text-base font-medium uppercase text-primary max-w-sm">{f.head}</div>
              {f.links.map((l)=>(
                      <a className="flex flex-col text-sm capitalize text-black py-2 hover:underline" >{l.name}</a>
                 ))}
             </div>
         })}
    </div>
  )
}

export default Footer