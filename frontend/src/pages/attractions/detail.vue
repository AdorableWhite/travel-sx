<template>
  <view class="container">
    <view class="hero">
      <text class="hero-title">{{ detail.name || '景点详情' }}</text>
      <text class="hero-sub">{{ detail.city }} · {{ detail.level }}</text>
    </view>

    <view class="section">
      <SectionTitle title="基础信息" />
      <InfoCard title="开放时间" :subtitle="detail.openTime || '暂无'"></InfoCard>
      <InfoCard title="门票价格" :subtitle="detail.ticketPrice ? `￥${detail.ticketPrice}` : '暂无'"></InfoCard>
      <InfoCard title="地址" :subtitle="detail.address || '暂无'"></InfoCard>
    </view>

    <view class="section">
      <SectionTitle title="深度导览" />
      <InfoCard title="导览简介">
        <view class="card-desc">{{ detail.intro || '暂无介绍' }}</view>
      </InfoCard>
      <InfoCard title="语音导览" :subtitle="detail.audioUrl ? '已接入' : '待接入'"></InfoCard>
    </view>

    <view class="section">
      <SectionTitle title="周边配套" />
      <InfoCard title="5km 内推荐" subtitle="厕所 · 停车场 · 餐厅"></InfoCard>
    </view>

    <button class="btn">预约门票（占位）</button>
  </view>
</template>

<script setup>
import { ref } from 'vue';
import { onLoad } from '@dcloudio/uni-app';
import { fetchAttractionDetail } from '../../services/api';
import { mockAttractions } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';

const detail = ref({});

const load = async (options = {}) => {
  const id = options.id || mockAttractions[0]?.id;
  try {
    const res = await fetchAttractionDetail(id);
    if (res && res.data) {
      detail.value = res.data;
    }
  } catch (e) {
    detail.value = mockAttractions.find((item) => `${item.id}` === `${id}`) || {};
  }
};

onLoad(load);
</script>

<style scoped lang="scss">
.hero {
  padding: 16px;
  background: #fff;
  border-radius: 12px;
  margin-bottom: 16px;
}

.hero-title {
  font-size: 20px;
  font-weight: 600;
}

.hero-sub {
  display: block;
  margin-top: 4px;
  color: #7a6f64;
  font-size: 12px;
}

.btn {
  background: #9a2e2e;
  color: #fff;
  border-radius: 8px;
  padding: 10px;
  margin-top: 10px;
}

.card-desc {
  font-size: 12px;
  color: #7a6f64;
}
</style>
