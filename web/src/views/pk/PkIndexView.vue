<template>
    <div class="start-board" v-if="$store.state.pk.starttime>0&&$store.state.pk.a_id === parseInt($store.state.user.id)">
        <div class="start-board-text">
            匹配成功，您为蓝色方，您的蛇位于左下角，游戏{{ $store.state.pk.starttime }}秒后开始
        </div>
    </div>
    <div class="start-board" v-if="$store.state.pk.starttime>0&&$store.state.pk.b_id === parseInt($store.state.user.id)">
        <div class="start-board-text">
            匹配成功，您为红色方，您的蛇位于右上角，游戏{{ $store.state.pk.starttime }}秒后开始
        </div>
    </div>
    <div class="card introduce">
        <div class="card-body">
            <div class="introduce-title">
                规则说明
            </div>
            <div class="introduce-body">
                1.您可以选择派AI出战或是自己亲自出战
            </div>
            <div class="introduce-body">
                2.本游戏为回合制游戏，若您亲自出战，您必须在五秒钟内按WASD选择一次方向
            </div>
            <div class="introduce-body">
                3.祝您游戏愉快！
            </div>
        </div>
    </div>
    <PlayGround v-if="$store.state.pk.status === 'playing'" />
    <MatchGround v-if="$store.state.pk.status === 'matching'" />
    <ResultBoard v-if="$store.state.pk.loser != 'none'" />
</template>

<script>
import PlayGround from '@/components/PlayGround.vue';
import MatchGround from '@/components/MatchGround.vue';
import ResultBoard from '@/components/ResultBoard.vue';
import { onMounted, onUnmounted } from 'vue';
import { useStore } from 'vuex';
export default {
    components: {
        PlayGround,
        MatchGround,
        ResultBoard,
    },
    setup() {
        const store = useStore();
        const socketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}/`;
        store.commit("updateLoser", "none");
        store.commit("updateIsRecord",false);
        let socket = null;

        onMounted(() => {
            store.commit("updateOpponent", {
                username: "您的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
            socket = new WebSocket(socketUrl);
            socket.onopen = () => {
                console.log("connected!");
                store.commit("updateSocket", socket);
            }
            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                console.log(data);
                if (data.event === "start-matching") {
                    store.commit("updateOpponent", {
                        username: data.opponent_username,
                        photo: data.opponent_photo,
                    });
                    store.state.pk.starttime = 3;
                    let updateStartTime = setInterval(() => {
                        store.state.pk.starttime--;
                        if (store.state.pk.starttime <= 0) {
                            clearInterval(updateStartTime); 
                        }
                    }, 1000)
                    setTimeout(() => {
                                store.commit("updateStatus", "playing");
                            }, 3100);
                    store.commit("updateGame", data.game);
                } else if (data.event === "move") {
                    // console.log(data);
                    const game = store.state.pk.gameObject;
                    const [snake0, snake1] = game.snakes;
                    snake0.set_direction(data.a_direction);
                    snake1.set_direction(data.b_direction);
                } else if (data.event === "result") {
                    // console.log(data);
                    const game = store.state.pk.gameObject;
                    const [snake0, snake1] = game.snakes;
                    if (data.loser === "all" || data.loser === "A") {
                        snake0.status = "die";
                    }
                    if (data.loser === "all" || data.loser === "B") {
                        snake1.status = "die";
                    }
                    store.commit("updateLoser", data.loser);
                }
            }
            socket.onclose = () => {
                console.log("disconnected!");
            }
        });
        onUnmounted(() => {
            socket.close();
            store.commit("updateStatus", "matching");
        })
    }
}
</script>

<style scoped>

div.start-board {
    height: 30vh;
    width: 30vw;
    background-color: rgba(50, 50, 50, 0.5);
    position: absolute;
    top: 30vh;
    left: 35vw;
}

div.start-board-text {
    text-align: center;
    color: white;
    font-size: 30px;
    font-weight: 400;
    font-style: italic;
    padding-top: 5vh;
}
.introduce {
    width: 15vw;
    height: 70vh;
    background-color: rgba(50, 50, 50, 0.5);
    position: absolute;
    top: 13.5vh;
    left: 2vw;
    border-radius: 1%;
}

div.introduce-title {
    color: white;
    font-size: 30px;
    font-weight: 400;
    padding-top: 1vh;
    font-style: italic;
    text-align: center;
}
div.introduce-body {
    font-size: 20px;
    font-weight: 300;
    padding-top: 1vh;
    font-style: italic;
    color: white;
}
</style>