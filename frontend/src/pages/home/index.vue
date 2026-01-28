<template>
  <view class="container">
    <view class="banner">
      <text class="banner-title">晋游记 · Shanxi Explorer</text>
      <text class="banner-sub">地上文物看山西，三晋风华一眼览</text>
    </view>

    <view class="quick-entry section">
      <view class="entry" @click="goTo('/pages/attractions/index')">景点预约</view>
      <view class="entry" @click="goTo('/pages/itinerary/index')">线路推荐</view>
      <view class="entry" @click="goTo('/pages/culinary/index')">美食地图</view>
      <view class="entry" @click="goTo('/pages/community/index')">游记社区</view>
      <view class="entry" @click="goTo('/pages/search/index')">全局搜索</view>
    </view>

    <view class="section">
      <SectionTitle title="热门景点" extra="悟空巡礼" />
      <InfoCard v-for="item in home.hotAttractions" :key="item.id" :title="item.name" :subtitle="item.city">
        <view class="card-desc">{{ item.intro }}</view>
      </InfoCard>
    </view>

    <view class="section">
      <SectionTitle title="当季必吃" extra="晋善晋美" />
      <InfoCard v-for="item in home.hotFoods" :key="item.id" :title="item.name" :subtitle="item.city">
        <view class="card-desc">评分 {{ item.rating || '--' }}</view>
      </InfoCard>
    </view>

    <view class="section">
      <SectionTitle title="话题圈子" extra="古韵探秘" />
      <InfoCard v-for="item in home.hotTopics" :key="item.id" :title="item.name" :subtitle="item.description" />
    </view>

    <view class="section">
      <SectionTitle title="精选游记" extra="最新分享" />
      <InfoCard v-for="item in home.hotNotes" :key="item.id" :title="item.title">
        <view class="card-desc">{{ item.content }}</view>
      </InfoCard>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchHome } from '../../services/api';
import { mockHome } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';

const home = ref({ ...mockHome });

const loadHome = async () => {
  try {
    const res = await fetchHome(6);
    if (res && res.data) {
      home.value = res.data;
    }
  } catch (e) {
    home.value = { ...mockHome };
  }
};

const goTo = (url) => {
  uni.navigateTo({ url });
};

onMounted(loadHome);
</script>

<style scoped lang="scss">
.banner {
  padding: 20px;
  border-radius: 12px;
  background: linear-gradient(120deg, #9a2e2e, #c9a063);
  color: #fff;
  margin-bottom: 16px;
}

.banner-title {
  font-size: 20px;
  font-weight: 700;
}

.banner-sub {
  display: block;
  margin-top: 6px;
  font-size: 12px;
  opacity: 0.9;
}

.quick-entry {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 8px;
}

.entry {
  background: #fff;
  border-radius: 8px;
  padding: 10px 8px;
  text-align: center;
  font-size: 12px;
  color: #2b2b2b;
  box-shadow: 0 1px 6px rgba(0, 0, 0, 0.04);
}

.card-desc {
  font-size: 12px;
  color: #7a6f64;
}
</style>
