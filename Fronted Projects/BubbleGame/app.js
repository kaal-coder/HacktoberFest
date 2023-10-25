var hitrn ;
var score =0;
var timer = 60;

function makeBubble(){
    var clutter = "";

for(let i=1; i<109; i++){
    hitrn = Math.floor(Math.random()*10)
    clutter += `<div class="bubble">${hitrn}</div>`
}
document.querySelector("#pbtm").innerHTML = clutter;
}


function runTimer(){
    var timerInterval = setInterval(function(){
        if(timer>0){
            timer --;
        document.querySelector("#timerVal").textContent = timer;
        }
        else{
            clearInterval(timerInterval)
        document.querySelector("#pbtm").innerHTML=`<h1>${"Game over"}</h1>`

        }
    },1000)
}

function getNewHit(){
    hitrn = Math.floor(Math.random()*10)
    document.querySelector("#hitValue").textContent = hitrn;

}

function increaseScore (){
    score += 10;
    document.querySelector("#scoreVal").textContent = score; 
}

document.querySelector("#pbtm").addEventListener("click", function(details){
    var clickednum = Number(details.target.textContent);
    if(clickednum === hitrn){
        console.log("problem")
    increaseScore();
    makeBubble();
    getNewHit();
    }
})

runTimer();
makeBubble();
getNewHit();



// 