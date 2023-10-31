package com.example.voicerecognition.base.util.picker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.voicerecognition.common.base.util.rememberDerivedState
import com.example.voicerecognition.common.base.util.rememberState
import kotlin.math.abs
import kotlin.math.ceil



@Composable
fun <ItemT> InfiniteItemsPicker(
    modifier: Modifier,
    items: List<ItemT>,
    chooseItemT: ItemT,
    onItemSelected: (ItemT?) -> Unit,
    heightLazyColumn: Dp = 230.dp,
    heightItem: Dp = heightLazyColumn * 0.15f,
    countItem: Int = Int.MAX_VALUE,
    itemPicker: @Composable BoxScope.(ItemT, VisiblyItem) -> Unit,
) {
    val startIndex: Int = remember { countItem / 2 }
    var currentValue by rememberState(items) { items.getOrNull(startIndex % items.size) }
    var isInit by rememberState() { false }

    val listState = rememberLazyListState(startIndex)
    val firstVisibleItemIndex by rememberDerivedState { listState.firstVisibleItemIndex }
    val visibleItems by rememberDerivedState { listState.layoutInfo.visibleItemsInfo.size }
    val isScrollInProgressStop = rememberIsScrollState(listState)

    LaunchedEffect(isScrollInProgressStop) {
        if (isInit) {
            onItemSelected(currentValue)
            listState.animateScrollToItem(index = firstVisibleItemIndex)
        }
    }
    LaunchedEffect(Unit) {
        var isSuccessSearch = false
        var start = startIndex
        while (!isSuccessSearch) {
            val item = items.getOrNull(start % items.size)
            if (item != chooseItemT) {
                start++
            } else {
                isSuccessSearch = true
            }
        }
        listState.animateScrollToItem(index = start - (visibleItems / 2 + 1))
        isInit = true
    }

    Box(
        modifier = modifier.height(heightLazyColumn),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = countItem, itemContent = { indexValue ->
                    val item by rememberState(indexValue, items) {
                        val index = indexValue % items.size
                        items.getOrNull(index)
                    }

                    val visiblyItem by rememberState(
                        firstVisibleItemIndex,
                        visibleItems,
                        indexValue
                    ) {
                        val position =
                            roundVisibleItemIndex(firstVisibleItemIndex, visibleItems, indexValue)
                        val visibly = VisiblyItem.getVisibly(position)
                        if (visibly == VisiblyItem.FIRST) {
                            currentValue = item
                        }
                        visibly
                    }

                    Box(
                        modifier = Modifier
                            .height(heightItem)
                            .offset(y = -heightItem * 1.5f)

                    ) {
                        item?.let { itItem -> itemPicker.invoke(this, itItem, visiblyItem) }
                    }
                })
            }
        )
    }
}

@Composable
fun rememberIsScrollState(listState: LazyListState): Boolean{
    var isSend by rememberState { false }
    val isScrollInProgress by rememberDerivedState { listState.isScrollInProgress }
    LaunchedEffect(!isScrollInProgress) {
        if (isScrollInProgress != isSend) {
            isSend = isScrollInProgress
        }
    }
    LaunchedEffect(isScrollInProgress) {
        if (isScrollInProgress != isSend) {
            isSend = isScrollInProgress
        }
    }
    return isSend
}


private fun roundVisibleItemIndex(
    firstVisibleItemIndex: Int,
    visibleItems: Int,
    indexValue: Int,
): Int {
    val numbVisibly = firstVisibleItemIndex + (visibleItems * 0.5) - indexValue
    return ceil(numbVisibly).toInt()
}

enum class VisiblyItem {
    FIRST,
    SECOND,
    THIRD,
    FORTH,
    OTHER;

    companion object {
        fun getVisibly(numb: Int) = when (abs(numb)) {
            0 -> FIRST
            1 -> SECOND
            2 -> THIRD
            3 -> FORTH
            else -> OTHER
        }
    }
}


