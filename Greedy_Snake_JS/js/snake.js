

const sw = 20, //width for a block
      sh = 20, //height for a block
      tr = 30, //# of rows
      td = 30; //# of columns

let snake = null //instance of snake;
let food = null;
let game = null;

////////////////////////-Square Configurations- //////////////////////////////////////////////////
function Square (x,y,classname){
  this.x = x*sw;
  this.y = y*sh;
  this.class = classname;
  this.viewContent = document.createElement('div');
  this.viewContent.className = this.class;
  this.parent = document.getElementById('snakeWrap');
}

Square.prototype.create = function(){ //create blocks DOM
  this.viewContent.style.position = 'absolute';
  this.viewContent.style.width = sw+'px';
  this.viewContent.style.height = sh +'px';
  this.viewContent.style.left = this.x +'px';
  this.viewContent.style.top = this.y + 'px';

  this.parent.appendChild(this.viewContent);
};

Square.prototype.remove = function(){
  this.parent.removeChild(this.viewContent);
};

////////////////////////-Snake Configuration - /////////////////////////////////////////////////
function Snake(){
  this.head = null; //store the head info
  this.tail = null
  this.pos = []; //store the each blocks location

  this.directionNum = {
    //4 directions, snakeHead rotation.
    left:{x: -1, y:0, rotate:-90},
    right:{x:1,y:0, rotate:90},
    up:{x:0,y:-1, rotate:0},
    down:{x:0,y:1, rotate:180}
  };
}
/*
Snake Initial Condition *****************************************************************
 */
Snake.prototype.init = function (){
  let snakeHead = new Square(2,0,'snakeHead');
  snakeHead.create();
  this.head = snakeHead;
  this.pos.push([2,0]); //saved the location of head

  //create body
  let snakeBody1 = new Square(1,0,'snakeBody');
  snakeBody1.create();
  this.pos.push([1,0]); //saved the location of body

  let snakeBody2 = new Square(0,0,'snakeBody');
  snakeBody2.create();
  this.tail = snakeBody2;
  this.pos.push([0,0]); //saved the location of body

  //Double LinkedList Concept for Snake body head connection.
  snakeHead.last = null;
  snakeHead.next = snakeBody1;
  snakeBody1.last = snakeHead;
  snakeBody1.next = snakeBody2;
  snakeBody2.last = snakeBody1;
  snakeBody2.next = null;

  //initial direction of movement
  this.direction = this.directionNum.right;
}
/*
Next block visiting conditions *****************************************************************
 */
Snake.prototype.getNextPos = function(){
  let nextPos = [
      this.head.x/sw+this.direction.x,
      this.head.y/sh+this.direction.y
  ]

  //Conditions for nextPos
  //1. if nextPos is to snake it self, game over
  let selfCollied = false;
  this.pos.forEach(function(value){
    if(value[0] == nextPos[0] && value[1] == nextPos[1]){
      selfCollied = true;
    }
  })

  if(selfCollied){
    console.log('Hit your self');
    this.strategies.die.call(this); //use .call to let this pointing to Snake object.
    return;
  }
  //2. if nextPos to the boundary, game over
  if(nextPos[0] < 0 || nextPos[1] <0 || nextPos[0] > td-1 || nextPos[1] > tr-1){
    console.log('Hit the wall');
    this.strategies.die.call(this);
    return;
  }

  //3. if nextPos is food, body+1;
  if(food && food.pos[0] == nextPos[0] && food.pos[1] == nextPos[1]){
    console.log('eat the food!!');
    this.strategies.eat.call(this);
    return;
  }

  //4. if nextPos empty, continue
  this.strategies.move.call(this);
}

/*
snake's three features *****************************************************************
 */
Snake.prototype.strategies={
  move: function (isEat){
    let newBody = new Square(this.head.x/sw, this.head.y/sh, 'snakeBody');

    //insert the newBody to the head position
    newBody.next = this.head.next;
    newBody.next.last = newBody;
    newBody.last = null;
    this.head.remove();
    newBody.create();

    //updated the head to the nestPos position
    let newHead = new Square(this.head.x/sw+this.direction.x,this.head.y/sh+this.direction.y, 'snakeHead');
    newHead.next = newBody;
    newHead.last = null;
    newBody.last = newHead;
    newHead.viewContent.style.transform = 'rotate('+this.direction.rotate+'deg)';
    newHead.create();


    /*
    Before: pos[body2, body1, head]
    Now: pos[body2, body1, head, newHead]
     */
    this.pos.splice(0,0,[this.head.x/sw+this.direction.x,this.head.y/sh+this.direction.y]);
    this.head = newHead;


    //remove the tail if not eat
    if(!isEat){
      this.tail.remove();
      this.tail = this.tail.last;
      this.pos.pop(); //pos pop item (tail)
    }

  },
  eat: function(){
    this.strategies.move.call(this,true);//letting move know snake eating.
    creatFood();//when eat, food vertex adjusted.
    game.score++;
  },
  die: function (){
    game.over();
  }
}

///////////////////////////////////-FOOD-/////////////////////////////////////////////////////
function creatFood(){
  let x = null;
  let y = null;

  let include = true; //flag for valid random vertex
  while(include){
    x = Math.round(Math.random()*(td-1)); //random val [0,1),hence times with max td.
    y = Math.round(Math.random()*(tr-1));

    snake.pos.forEach(function(value){
      if(x!=value[0] && y!=value[1]){
        //the vertex (x,y) are on the snake
        include = false;
      }
    })
  }

  //generate food
  food = new Square(x,y,'food');
  food.pos = [x,y]; //store the location of the food, use for nextPos of the snack.

  //use to adjusting the vertex for a existing food
  let foodDom = document.querySelector('.food');
  if(foodDom){
    foodDom.style.left = x*sw+'px';
    foodDom.style.top = y*sh+'px';
  }else{
    food.create();
  }
}



////////////////////////////////////////-Game Configuration - ///////////////////////////////////////
function Game(){
  this.timer = null;
  this.score = 0;
}

Game.prototype.init = function(){
  snake = new Snake();
  snake.init();
  creatFood();
  snake.getNextPos();

  //keyboard event
  document.onkeydown = function(key) {
    if (key.which == 37 && snake.direction != snake.directionNum.right) {  //left
      snake.direction = snake.directionNum.left;
    }else if (key.which == 38 && snake.direction != snake.directionNum.down) { //up
      snake.direction = snake.directionNum.up;
    }else if (key.which == 39 && snake.direction != snake.directionNum.left) { //right
      snake.direction = snake.directionNum.right;
    }else if (key.which == 40 && snake.direction != snake.directionNum.up) {  //down
      snake.direction = snake.directionNum.down;
    }
  }
  this.start();
}

Game.prototype.start= function (){
  this.timer = setInterval(function(){
    snake.getNextPos();
  },200);
}
Game.prototype.pause= function(){
  clearInterval(this.timer);
}

Game.prototype.over = function(){
  clearInterval(this.timer);
  alert('Your Score is: ' + this.score);

  //reset
  let snakeWrap = document.getElementById('snakeWrap');
  snakeWrap.innerHTML = '';

  snake = new Snake();
  game = new Game();
  let startBtnWrap = document.querySelector('.startBtn');
  startBtnWrap.style.display = 'block';
}


//Game Start <BUTTON>
game = new Game();
let startBtn = document.querySelector('.startBtn button');
startBtn.onclick = function(){
  startBtn.parentNode.style.display = 'none';
  game.init();
}

//pause <BUTTON>
let snakeWrap = document.getElementById('snakeWrap');
let pauseBtn = document.querySelector('.pauseBtn button');
snakeWrap.onclick = function(){
  game.pause();
  pauseBtn.parentNode.style.display = 'block';
}

//resume <BUTTON>
pauseBtn.onclick = function(){
  game.start();
  pauseBtn.parentNode.style.display = 'none';
}


