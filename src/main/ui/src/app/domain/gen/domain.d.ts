// Generated using typescript-generator version 1.24.318 on 2017-06-14 21:26:42.

declare namespace Domain {

    interface AbstractTask extends Entity {
        description: string;
    }

    interface Entity {
        id: string;
    }

    interface KeyValue {
        key: string;
        value: string;
    }

    interface QuartzJob extends Job {
    }

    interface ScheduledTask extends AbstractTask {
        taskGroupId: string;
        taskId: string;
        cron: string;
    }

    interface Task extends AbstractTask {
        taskGroupId: string;
        headers: KeyValue[];
        protocol: string;
        domain: string;
        resourcePath: string;
        port: string;
        method: Method;
        queryParams: KeyValue[];
        body: string;
    }

    interface TaskGroup extends AbstractTask {
    }

    interface TaskResult extends Entity {
        taskId: string;
        dateTime: Date;
        taskResult: string;
    }

    interface Job {
    }

    type Method = "GET" | "POST" | "DELETE" | "PUT";

}
