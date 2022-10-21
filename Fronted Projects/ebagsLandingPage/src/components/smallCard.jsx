import React from 'react'
import '../index.css'

const SmallCard = ({img}) => {
  return (
    <>
         <div className="py-5" >
          <div className="max-w-sm" >
            <img src="https://cdn-fsly.yottaa.net/54636bb786305e35ea00040e/www.ebags.com/v~4b.692/dw/image/v2/AAUE_PRD/on/demandware.static/-/Sites-ebags-Library/default/dw90662c4f/slots/content-blocks/2022%20Content%20Blocks/ebags-content-block-luxon-travelbp.jpg?sw=1500&sfrm=jpg&q=80&yocs=4p_4t_" alt="" />
          </div>
           <div className="mt-5 text-xl text-primary font-semibold">Luxon Travel Backpack</div>
           <div className="text-base text-black font-base">Now only $109.99</div>
           <div className="text-base text-primary font-semibold">Shop Now</div>
         </div>

         <div className="py-5" >
          <div className="max-w-sm" >
            <img src="https://cdn-fsly.yottaa.net/54636bb786305e35ea00040e/www.ebags.com/v~4b.692/dw/image/v2/AAUE_PRD/on/demandware.static/-/Sites-ebags-Library/default/dwd54d9f0f/slots/content-blocks/2022%20Content%20Blocks/ebags-content-block-cts-daypack.jpg?sw=1500&sfrm=jpg&q=80&yocs=4p_4t_" alt="" />
            <div className="mt-5 text-xl text-primary font-semibold">Pro Slim Series</div>
           <div className="text-base text-black font-base">Starting at $59.99</div>
           <div className="text-base text-primary font-semibold">Shop Now</div>
          </div>
         </div>
         <div className="py-5" >
          <div className="max-w-sm" >
            <img src="https://cdn-fsly.yottaa.net/54636bb786305e35ea00040e/www.ebags.com/v~4b.692/dw/image/v2/AAUE_PRD/on/demandware.static/-/Sites-ebags-Library/default/dwff93c03d/slots/content-blocks/2022%20Content%20Blocks/ebags-content-block-pro-slim-laptopbp-graphite.jpg?sw=1500&sfrm=jpg&q=80&yocs=4p_4t_" alt="" />
            <div className="mt-5 text-xl text-primary font-semibold">CTS Daypack</div>
           <div className="text-base text-black font-base">Now only $69.99</div>
           <div className="text-base text-primary font-semibold">Shop Now</div>
          </div>
         </div>
         </>
  )
}

export default SmallCard