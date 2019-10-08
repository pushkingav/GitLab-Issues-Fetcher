import React from "react";
// react plugin for creating charts
// @material-ui/core
import {makeStyles} from "@material-ui/core/styles";
// @material-ui/icons
// core components
import Button from "components/CustomButtons/Button.js";
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardBody from "components/Card/CardBody.js";
import CardFooter from "components/Card/CardFooter.js";
import CustomInput from "components/CustomInput/CustomInput.js";
import Tasks from "../../components/Tasks/Tasks";

import axios from "axios";
import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";
import {milestones} from "variables/general.js"

const combinedStyles = {
    ...styles,
    cardCategoryWhite: {
        color: "rgba(255,255,255,.62)",
        margin: "0",
        fontSize: "14px",
        marginTop: "0",
        marginBottom: "0"
    },
    cardTitleWhite: {
        color: "#FFFFFF",
        marginTop: "0px",
        minHeight: "auto",
        fontWeight: "300",
        fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
        marginBottom: "3px",
        textDecoration: "none"
    }
};
const useStyles = makeStyles(combinedStyles);

export default function Dashboard() {
    const classes = useStyles();
    const getMilestones = (searchStr) => {
      axios.get('http://localhost:18080/milestones/${searchStr}').then(({data}) => {
          console.log(data);
          this.setState({
              milestones: data
          })
      })
    };
    return (
        <div>
            <GridContainer>
                <GridItem xs={12} sm={12} md={8}>
                    <Card>
                        <CardHeader color="primary">
                            <h4 className={classes.cardTitleWhite}>Select Milestones</h4>
                            <p className={classes.cardCategoryWhite}>Choose Milestones to list issues...</p>
                        </CardHeader>
                        <CardBody>
                            <GridContainer alignItems={"flex-end"}>
                                <GridItem xs={12} sm={12} md={4}>
                                    <CustomInput
                                        labelText="Search Milestones"
                                        id="searchMilestone"
                                        formControlProps={{
                                            fullWidth: true
                                        }}
                                    />
                                </GridItem>
                                <GridItem xs={12} sm={12} md={4}>
                                    <Button color="primary">Search</Button>
                                </GridItem>
                                <GridItem xs={12} sm={12} md={5}>
                                    <Tasks
                                        checkedIndexes={[1]}
                                        tasksIndexes={[0, 1, 2]}
                                        tasks={milestones}
                                    />
                                </GridItem>
                            </GridContainer>
                        </CardBody>
                        <CardFooter>
                            <Button color="primary">List Issues</Button>
                        </CardFooter>
                    </Card>
                </GridItem>
            </GridContainer>
        </div>
    );
}
