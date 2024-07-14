package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Demo1 {
    @Test
    public void mockObjectTest() {
        //创建 mock 对象
        ArrayList mockList = mock(ArrayList.class);

        mockList.add("one");
        //verify 是否 invoke method add() param "one"
        verify(mockList).add("one");
        //size 还没设置值，为0
        assertEquals(0, mockList.size());
        //mock method size() and return value
        when(mockList.size()).thenReturn(100);
        //get mock method size value
        assertEquals(100, mockList.size());
    }

    //相当于 Mockito.mock(ArrayList.class);
    @Mock
    List<String> mockedList;
    @Test
    public void annotationMockTest() {
        assertNotNull(mockedList);
    }

    @Test
    public void spyExistInstanceTest() {
        // spy mock exist instance
        List<String> list = spy(new ArrayList<>());

        list.add("one");
        verify(list).add("one");
        list.add("two");
        verify(list).add("two");

        //实例对象不 mock side() 也有真实的值
        assertEquals(2, list.size());
        //也可以mock size()
        when(list.size()).thenReturn(100);
        assertEquals(100, list.size());
    }

    //相当于 Mockito.spy(new ArrayList<>());
    @Spy
    List<String> spiedList = new ArrayList<>();
    @Test
    public void spyAnnotationTest() {
        when(spiedList.size()).thenReturn(100);
        assertEquals(100, spiedList.size());
    }

    @Test
    public void captureArgTest() {
        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);

        List list = mock(List.class);
        list.add("one");

        //add 方法执行后捕获参数
        verify(list).add(arg.capture());
        assertEquals("one", arg.getValue());
    }

    //相当于 ArgumentCaptor.forClass(String.class)
    @Captor
    ArgumentCaptor<String> argCaptor;
    @Test
    public void captureArgAnnotationTest() {
        List list = mock(List.class);
        list.add("one");

        //add 方法执行后捕获参数
        verify(list).add(argCaptor.capture());
        assertEquals("one", argCaptor.getValue());
    }

    @Mock
    Map<String, String> wordMap;

    //注入 mock 对象 wordMap
    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Test
    public void injectMockTest() {
        //mock map 对象
        when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", dic.get("aWord"));
    }

}
