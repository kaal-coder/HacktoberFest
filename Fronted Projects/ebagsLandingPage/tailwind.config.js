/** @type {import('tailwindcss').Config} */

module.exports = {
  mode : 'jit',
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",

  ],
  theme: {
    extend: {
      fontFamily:{
        'Inter' : ['Inter var', 'sans-serif']
      },
      backgroundImage : {
        Hero : "url('https://www.ebags.com/dw/image/v2/AAUE_PRD/on/demandware.static/-/Sites-ebags-Library/default/dwc70f9528/slots/fullwidth-banner/2022%20Banners/2022-06-08-EBAGS-HomepageHero-Citylink-Desktop-V2.jpg?sw=2160&amp;sfrm=jpg&amp;q=90')"
      },
      colors : {
        primary : '#7d8dae'
      }
    },
  },
  plugins: [],
}
