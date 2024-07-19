let anim = document.querySelectorAll(".conv-anim");
let animInv = document.querySelectorAll(".conv-anim-inv");

let ingImgArr = ['beacon.png', 'cheese.png',
      'cucumber.png', 'gorchica.png', 'ketchupe.png', 'peper.png',
      'peperoni.png', 'tabasco.png', 'toast.png', 'tomatoes.png'
]

for(let i = 0; i < 3; i++){
      anim[i].style.animationDelay = Math.floor(Math.random() * (8 - 1) + 1) + "s";
}

for(let i = 0; i < 2; i++){
      animInv[i].style.animationDelay = Math.floor(Math.random() * (8 - 1) + 1) + "s";
}

let ingArr = document.querySelectorAll(".ingredient");

for(let i = 0; i < 50; i++){
      ingArr[i].style.backgroundImage = "url('../images/" + ingImgArr[Math.floor(Math.random() * 10)] + "')";
}
