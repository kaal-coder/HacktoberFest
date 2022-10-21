import React, { useState } from "react";
import { Link } from "react-router-dom";
import { links } from "./Mylinks";

const NavLinks = () => {
  const [heading, setHeading] = useState("");
  const [subHeading, setSubHeading] = useState("");
  return (
    <>


      {links.map((link) => (
        <div>
          <div className="px-3 text-left md:cursor-pointer group">
            <p
              className="py-7 flex justify-between items-center md:pr-0 pr-5 group"
              onClick={() => {
                heading !== link.name ? setHeading(link.name) : setHeading("");
                setSubHeading("");
              }}
            >
              {link.name}
                     <i
                        className={`${
                          heading === link.name
                            ? "fa fa-angle-up md:hidden"
                            : "fa fa-angle-down md:hidden"
                        }`}
                      ></i>
               
          
            </p>

       

            {link.submenu && (
              <div>
                <div className="absolute hidden group-hover:md:block hover:md:block overflow-hidden">
                  <div className="-my-1">
  
                  </div>
                  <div className="bg-white p-16 grid grid-cols-3 gap-10 ">
                    {link.sublinks.map((mysublinks) => (
                      <div>
                        {mysublinks.sublink.map((slink) => (
                          <li className="text-sm font-medium capitalize text-primary my-2.5">
                            <Link
                              to={slink.link}
                              className="hover:underline"
                            >
                              {slink.name}
                            </Link>
                          </li>
                        ))}
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            )}
          </div>
          {/* Mobile menus */}
          <div
            className={`
            ${heading === link.name ? "md:hidden" : "hidden"}
          `}
          >
            {/* sublinks */}
            {link.sublinks.map((slinks) => (
              <div>
                <div>
                  <h1
                    onClick={() =>
                      subHeading !== slinks.Head
                        ? setSubHeading(slinks.Head)
                        : setSubHeading("")
                    }
                    className="py-4 pl-7 font-semibold md:pr-0 pr-5 flex justify-between items-center md:pr-0 pr-5"
                  >
                    {slinks.Head}

                    <span className="text-2xl md:mt-1 md:ml-2 inline">
                      <i
                        className={`${
                          subHeading === slinks.Head
                            ? "fa fa-angle-up"
                            : "fa fa-angle-down"
                        }`}
                      ></i>
                    </span>
                  </h1>
                  <div
                    className={`${
                      subHeading === slinks.Head ? "md:hidden" : "hidden"
                    }`}
                  >
                    {slinks.sublink.map((slink) => (
                      <li className="py-3 pl-14">
                        <Link to={slink.link}>{slink.name}</Link>
                      </li>
                    ))}
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      ))}
           <div className="w-auto">
            <input className="text-sm hidden lg:inline font-medium border-b border-primary mx-16 w-64 px-6 " placeholder='Search backpacks,luggage..' type="text" />
            <div className="p-1 rounded-full bg-primary w-6 absolute right-16 text-center md:mt-0 md:top-7 text-white" >0</div>
            <i className="fa fa-shopping-bag text-xl absolute" ></i>
         </div>
    </>
  );
};

export default NavLinks;