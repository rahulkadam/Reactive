package com;

import com.rx.dto.Result;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode {

    public static void main(String args[]) {
        LeetCode leetCode = new LeetCode();
        String[] query = {"N,1,0000001,AB,L,B,104.53,100" ,
                "N,2,0000001,AB,L,B,104.53,100" ,
                "N,3,0000001,AB,L,B,104.53,100" ,
                "N,12,0000001,XYZ,L,B,103.53,150" ,
                "A,2,0000001,AB,L,B,104.53,150" ,
                "N,4,0000001,XYZ,L,S,103.53,150" ,
                "N,5,0000001,AB,L,S,104.53,80" ,
                "N,6,0000001,AB,L,S,104.53,20" ,
                "X,1,0000001",
                "X,11,0000001",
                "Q"};
        String q[] = {
                "N,1,0000001,ALN,L,B,60.90,100",
                "N,13,0000002,ALN,L,B,60.90,100",
                "N,10,0000003,ALN,L,S,60.90,100",
                "N,12,0000004,ALN,L,S,60.90,100",
                "N,11,0000005,ALB,L,S,60.90,100",
                "N,14,0000006,ALB,L,S,62.90,101",
                "N,16,0000007,ALB,L,S,63.90,102",
                "N,18,0000008,ALB,L,S,64.90,103",
                "N,20,0000009,ALB,L,S,65.90,104",
                "Q,0000003" ,
                "Q,ALB",
                "Q,ALN,0000002",
                "Q,0000002,ALN",
                "Q"
        };
        String [] sampleInput= {
            "N,1,0000001,AB,L,B,104.53,100",
            "N,2,0000002,AB,L,S,105.53,100",
            "N,3,0000003,AB,L,B,104.53,90",
  //          "M,0000004",
            "N,4,0000005,AB,L,S,104.43,80" ,
            "A,2,0000006,AB,L,S,104.42,100",
            "Q",
//            "M,0000008",
            "N,5,0000009,AB,L,S,105.53,120",
            "X,3,0000010",
            "N,6,0000011,XYZ,L,B,1214.82,2568",
            "Q"
        };

        String matchQuery[] = {
                "N,1,0000001,ALN,L,B,60.90,100",
                "N,11,0000002,XYZ,L,B,60.90,200",
                "N,110,0000003,XYZ,L,S,60.90,100",
                "N,112,0000003,XYZ,L,S,60.90,120",
                "N,10,0000006,ALN,L,S,60.90,100",
                "M,00010",
                "M,00010,ALN"
        };
        String result[] = processQueries(matchQuery);
        for(int i=0;i < result.length;i++) {
            System.out.println(result[i]);
        }
    }

    static String[] processQueries(String[] queries) {
        // Write your code here.
        Map<Long,HashMap<String, Object>> orderDetails = new HashMap<>();
        Map<String,HashMap<Long, HashMap<String, Object>>> buyOrders = new HashMap<>();
        Map<String,HashMap<Long, HashMap<String, Object>>> sellOrders = new HashMap<>();
        List<HashMap<String, Object>> orderList = new ArrayList<>();

        List<String> resultList = new ArrayList<>();
        for(String query : queries) {
            String actionType = getActionType(query);
            switch(actionType) {
                case "N":
                    try {
                        HashMap<String, Object> orderMap = getOrderMap(query);
                        boolean validate = validateNewOrder(orderDetails, orderMap);
                        if (validate) {
                            orderDetails.put((Long)orderMap.get("OrderId") , orderMap);
                            orderList.add(orderMap);
                            resultList.add((Long)orderMap.get("OrderId") + " - Accept");
                        } else {
                            resultList.add((Long)orderMap.get("OrderId") + " - Reject - 303 - Invalid order details");
                        }
                    } catch (Exception e) {
                        resultList.add(" - Reject - 303 - Invalid order details");
                    }
                    break;
                case "A" :
                    try {
                        HashMap<String, Object> orderMap = getOrderMap(query);
                        boolean validateExistance = validateNewOrder(orderDetails, orderMap);
                        if (validateExistance) {
                            resultList.add((Long)orderMap.get("OrderId") + " - AmendReject - 404 - Order does not exist");
                        } else {
                            boolean amendValidate = validateAmendOrder(orderDetails, orderMap);
                            if (amendValidate) {
                                orderDetails.put((Long)orderMap.get("OrderId") , orderMap);
                                resultList.add((Long)orderMap.get("OrderId") + " - AmendAccept");
                            } else {
                              resultList.add((Long)orderMap.get("OrderId") + " - AmendReject - 101 - Invalid amendment details");
                            }
                        }
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
                    break;
                case "X" :
                        try{
                            Long orderId = getOrderId(query);
                            boolean validateCancel = validateCancelOrder(orderId , orderDetails);
                            if (validateCancel) {
                                orderDetails.remove(orderId);
                                resultList.add(orderId + " - CancelAccept");
                            } else {
                                resultList.add(orderId + " - CancelReject - 404 - Order does not exist");
                            }
                        } catch(Exception e) {

                        }
                    break;
                case "Q":
                    List<HashMap<String, Object>> queryResult = fetchQueryResult(query , orderList);
                    manageQueryResult(queryResult,resultList);
                    break;
                case "M":
                    List<HashMap<String, Object>> matchResult = fetchQueryResult(query , orderList);
                    manageMatchResult(matchResult,resultList , orderList);
                    break;
                default:
                    break;
            }
        }
        String [] resultString =(String[])resultList.toArray(new String[resultList.size()]);
        return resultString;
    }

    static boolean validateNewOrder(Map<Long,HashMap<String, Object>> orderDetails , HashMap<String, Object> orderMap) {

        try {
            if (!orderDetails.containsKey((Long)orderMap.get("OrderId"))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    static void manageQueryResult(List<HashMap<String, Object>> orderList , List<String> resultList) {
        Set<String> uniqueProductList = orderList.stream().map(a -> (String)a.get("Symbol")).collect(Collectors.toCollection(TreeSet::new));

        uniqueProductList.forEach(order -> {
            List<HashMap<String, Object>> buyerList = orderList.stream().filter(a -> {
                return ((String) a.get("Side")).equals("B") && a.get("Symbol").equals(order);
            }).sorted((a, b) -> {
                return ((Float) a.get("Price")).compareTo((Float) b.get("Price"));
            }).collect(Collectors.toList());


            List<HashMap<String, Object>> sellerList = orderList.stream().filter(a -> {
                return ((String) a.get("Side")).equals("S") && a.get("Symbol").equals(order);
            }).sorted((a, b) -> {
                return ((Float) a.get("Price")).compareTo((Float) b.get("Price"));
            }).collect(Collectors.toList());

            Iterator<HashMap<String, Object>> buyIterator = buyerList.listIterator();
            int i=0;
            while(buyIterator.hasNext() && i < sellerList.size()) {
                HashMap<String, Object> buyerBid = buyIterator.next();
                HashMap<String, Object> sellerBid = sellerList.get(i);
                if ((Float)sellerBid.get("Price") <= (Float)buyerBid.get("Price") && (Long)sellerBid.get("Quantity") <= (Long)buyerBid.get("Quantity")){
                    updateResultList(resultList , buyerBid , sellerBid , order);
                    i++;
                } else {
                    updateResultList(resultList , buyerBid , null , order);
                }
            }
            while(buyIterator.hasNext()) {
                HashMap<String, Object> buyerBid = buyIterator.next();
                updateResultList(resultList , buyerBid , null , order);
            }
            while(i < sellerList.size()) {
                updateResultList(resultList , null , sellerList.get(i) , order);
                i++;
            }
        });
    }

    static void manageMatchResult(List<HashMap<String, Object>> orderList , List<String> resultList , List<HashMap<String, Object>> orderUpdatedList) {
        Set<String> uniqueProductList = orderList.stream().map(a -> (String)a.get("Symbol")).collect(Collectors.toCollection(TreeSet::new));

        uniqueProductList.forEach(order -> {
            List<HashMap<String, Object>> buyerList = orderList.stream().filter(a -> {
                return ((String) a.get("Side")).equals("B") && a.get("Symbol").equals(order);
            }).sorted((a, b) -> {
                return ((Float) a.get("Price")).compareTo((Float) b.get("Price"));
            }).collect(Collectors.toList());

            List<HashMap<String, Object>> sellerList = orderList.stream().filter(a -> {
                return ((String) a.get("Side")).equals("S") && a.get("Symbol").equals(order);
            }).sorted((a, b) -> {
                return ((Float) b.get("Price")).compareTo((Float) a.get("Price"));
            }).collect(Collectors.toList());

            Iterator<HashMap<String, Object>> buyIterator = buyerList.listIterator();
            int sellerIndex = 0;
            int buyerIndex = 0;
            while( buyerIndex < buyerList.size() && sellerIndex < sellerList.size()) {
                HashMap<String, Object> buyerBid = buyerList.get(buyerIndex);
                HashMap<String, Object> sellerBid = sellerList.get(sellerIndex);
                if ((Float)sellerBid.get("Price") <= (Float)buyerBid.get("Price")){
                    if ((Long)sellerBid.get("Quantity") >= (Long)buyerBid.get("Quantity")) {
                        sellerBid.put("Quantity", (Long) sellerBid.get("Quantity") - (Long) buyerBid.get("Quantity"));
                        buyerIndex++;
                    } else {
                        buyerBid.put("Quantity", (Long) buyerBid.get("Quantity") - (Long) sellerBid.get("Quantity"));
                        sellerIndex++;
                    }
                    updateResultList(resultList , buyerBid , sellerBid , order);
                } else {
                    buyerIndex++;
                }
            }
        });
    }

    static void updateResultList(List<String> resultList , HashMap<String, Object> buyerBid , HashMap<String, Object> sellerBid , String symbol) {
        String result = symbol +"|";
        if (buyerBid != null) {
            result += buyerBid.get("OrderId") +","+buyerBid.get("OrderType") +"," + buyerBid.get("Quantity") + "," + buyerBid.get("Price") +"|";
        } else {
            result += "|";
        }
        if (sellerBid != null) {
            result += sellerBid.get("Price") +","+sellerBid.get("Quantity") +"," + sellerBid.get("OrderType") + "," + sellerBid.get("OrderId");
        }
        resultList.add(result);
    }


    static List<HashMap<String, Object>> fetchQueryResult(String order , List<HashMap<String, Object>> orderList) {
        String orderDetails[] = order.split(",");
        List<HashMap<String, Object>> resultlist = null;
        if (orderDetails.length ==1) {
             resultlist = orderList.stream().sorted((a, b)-> {
                 return ((String)a.get("Symbol")).compareTo((String)b.get("Symbol"));
             }).collect(Collectors.toList());
        } else if(orderDetails.length ==2) {
            String str = orderDetails[1];
            try {
                Long timeStamp = Long.parseLong(str);
                resultlist = orderList.stream().filter(obj -> {
                    return (Long)obj.get("Timestamp") <= timeStamp;
                }).collect(Collectors.toList());
            } catch(Exception e){
                 resultlist = orderList.stream().filter(obj -> {
                    return obj.get("Symbol").equals(str);
                }).collect(Collectors.toList());
            }
        } else if(orderDetails.length ==3) {
            String str = orderDetails[1];
            String secondArg = orderDetails[2];
            try {
                Long timeStamp = Long.parseLong(str);
                resultlist = orderList.stream().filter(obj -> {
                    return (Long)obj.get("Timestamp") <= timeStamp && secondArg.equals(obj.get("Symbol"));
                }).collect(Collectors.toList());
            } catch(Exception e){
                resultlist = orderList.stream().filter(obj -> {
                    return obj.get("Symbol").equals(str) && (Long)obj.get("Timestamp") <= Long.parseLong(secondArg);
                }).collect(Collectors.toList());
            }
        }
        return resultlist;
    }

    static boolean validateCancelOrder(Long orderId , Map<Long,HashMap<String, Object>> orderDetails) {

        try {
            if (orderDetails.containsKey(orderId)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    static boolean validateAmendOrder(Map<Long,HashMap<String, Object>> orderDetails , HashMap<String, Object> orderMap) {
        HashMap<String, Object> existingOrder = orderDetails.get((Long)orderMap.get("OrderId"));
        if (existingOrder.get("Side").equals(orderMap.get("Side")) &&
                existingOrder.get("OrderType").equals(orderMap.get("OrderType")) && existingOrder.get("Symbol").equals(orderMap.get("Symbol"))) {
            return true;
        }
        return false;
    }

    static HashMap<String, Object> getOrderMap(String order)  throws Exception{
        HashMap<String, Object> orderMap = new HashMap<>();
        String orderDetails[] = order.split(",");
        int i=1;
        Long id = 0L;
        for(String str : orderDetails) {
            switch (i) {
                case 1:
                    orderMap.put("Action" , str);
                    break;
                case 2:
                    id = Long.parseLong(str);
                    orderMap.put("OrderId" , id);
                    break;
                case 3:
                    id = Long.parseLong(str);
                    orderMap.put("Timestamp" , id);
                    break;
                case 4:
                    orderMap.put("Symbol" , str);
                    break;
                case 5:
                    orderMap.put("OrderType" , str);
                    break;
                case 6:
                    orderMap.put("Side" , str);
                    break;
                case 7:
                    Float price = Float.parseFloat(str);
                    orderMap.put("Price" , price);
                    break;
                case 8:
                    id = Long.parseLong(str);
                    orderMap.put("Quantity" , id);
                    break;
                default :
                    break;
            }
            i++;
        }

        if (i!=9) {
            throw new Exception("invalid request");
        }
        return orderMap;
    }

    static String getActionType(String order) {
        String orderDetails[] = order.split(",");
        return orderDetails[0];
    }

    static Long getOrderId(String order) {
        String orderDetails[] = order.split(",");
        return Long.parseLong(orderDetails[1]);
    }
}
